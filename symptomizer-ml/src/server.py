from tornado.web import Application, RequestHandler
from tornado.ioloop import IOLoop


class MainHandler(RequestHandler):
    def data_received(self, chunk):
        pass

    def get(self):
        self.write({'message': 'hello world'})


def bootstrap():
    urls = [("/", MainHandler)]
    return Application(urls)


if __name__ == '__main__':
    app = bootstrap()
    app.listen(8081)
    IOLoop.instance().start()
