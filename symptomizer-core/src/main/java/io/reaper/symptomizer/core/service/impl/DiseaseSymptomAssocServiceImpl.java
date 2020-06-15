package io.reaper.symptomizer.core.service.impl;

import io.reaper.symptomizer.core.model.dto.ReportDto;
import io.reaper.symptomizer.core.repo.impl.DiseaseNativeRepo;
import io.reaper.symptomizer.core.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @author Sanzhar Kudaibergen
 */
@Service
@RequiredArgsConstructor
public class DiseaseSymptomAssocServiceImpl implements ImportService {

    private final DiseaseNativeRepo diseaseNativeRepo;

    @Override
    @Transactional
    public ReportDto importFromFile(MultipartFile file) throws IOException {
        assertIsCsv(file);

        List<String> symptomCodesToSave = null;
        Stack<String> diseaseCodes = null;
        Map<String, List<String>> diseaseSymptomCodes = null;
        String row, diseaseCode = null, symptomCode = null;

        try (InputStream is = new ByteArrayInputStream(file.getBytes());
             InputStreamReader isReader = new InputStreamReader(is);
             BufferedReader buffReader = new BufferedReader(isReader)) {
            diseaseSymptomCodes = new LinkedHashMap<>();
            diseaseCodes = new Stack<>();
            symptomCodesToSave = new ArrayList<>();

            buffReader.readLine();
            while ((row = buffReader.readLine()) != null) {
                String[] cols = row.split(",");

                diseaseCode = cols[0];
                symptomCode = cols[2];


                if (!diseaseCodes.empty()) {
                    String peek = diseaseCodes.peek();

                    if (!peek.equals(diseaseCode)) {
                        String popped = diseaseCodes.pop();
                        diseaseSymptomCodes.put(popped, symptomCodesToSave);
                        symptomCodesToSave = new ArrayList<>();
                        diseaseCodes.push(diseaseCode);
                    }
                    else {
                        symptomCodesToSave.add(symptomCode);
                    }
                } else {
                    diseaseCodes.push(diseaseCode);
                    symptomCodesToSave.add(symptomCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (diseaseSymptomCodes != null && !diseaseSymptomCodes.isEmpty()) {
            diseaseSymptomCodes.forEach((k, v) -> {
                for (String sCode: v) {
                    diseaseNativeRepo.saveAssociation(k, sCode);
                }
            });
        }

        return null;
    }

    @Override
    public byte[] exportToFile() throws IOException {
        return new byte[0];
    }

}
