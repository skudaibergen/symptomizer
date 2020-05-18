import logging

from tornado.escape import json_decode
from tornado.web import Application, RequestHandler
from tornado.ioloop import IOLoop

from symptom_analyzer import SymptomAnalyzer


class MainHandler(RequestHandler):
    def data_received(self, chunk):
        pass

    def get(self):
        self.write({'message': 'hello world'})


class DiseasePredictionHandler(RequestHandler):
    def data_received(self, chunk):
        pass

    def post(self, *args, **kwargs):
        if self.request.headers['Content-Type'] == 'application/json':
            body = json_decode(self.request.body)
            analyzer = SymptomAnalyzer(body['symptomCodes'])
            prediction = analyzer.predict()
            self.write({
                'status': 'success',
                'data': {
                    'predictedDisease': {
                        'code': prediction
                    }
                }
            })
        else:
            self.write({
                'status': 'error',
                'error': 'unknown prediction'
            })


def bootstrap():
    urls = [
        ("/", MainHandler),
        ("/predict/disease", DiseasePredictionHandler)
    ]
    return Application(urls)


if __name__ == '__main__':
    log = logging.getLogger('Main logger')
    log.info("Starting %s...", 'symptomizer-ml')

    app = bootstrap()
    app.listen(8001)
    IOLoop.instance().start()
