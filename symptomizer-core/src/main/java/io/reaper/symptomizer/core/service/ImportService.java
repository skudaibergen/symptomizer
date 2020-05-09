package io.reaper.symptomizer.core.service;

import com.google.common.base.Preconditions;
import io.reaper.symptomizer.core.model.dto.ReportDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 * @author Sanzhar Kudaibergen
 */
public interface ImportService {

    default void assertIsCsv(MultipartFile file) {
        Preconditions.checkArgument(file != null, "File to upload cannot be null.");
        Preconditions.checkArgument(file.getContentType() != null, "File content-type cannot be null.");
        Preconditions.checkArgument(file.getContentType().equals("text/csv"), "Only .csv files are eligible to upload for now.");
    }

    ReportDto importFromFile(MultipartFile file) throws IOException;

}
