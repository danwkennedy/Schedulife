var http = require('http');
http.createServer(function (req, res) {
    res.writeHead(200, { 'Content-Type': 'text/plain' });
    res.end('Welcome to Schedulife!\n');
}).listen(1337, '127.0.0.1');
console.log('Server running at http://localhost:1337/');