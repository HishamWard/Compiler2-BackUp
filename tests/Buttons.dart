import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Buttons',
      home: Buttons(),
    );
  }
}

class Buttons extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.orange,
        child: Padding(
          padding: EdgeInsets.only(left: 110, top: 100, right: 0, bottom: 0),
          child: Column(
            children: [
              Row(children: [
                Padding(
                  padding:
                  EdgeInsets.only(left: 0, top: 0, right: 70, bottom: 0),
                  child: Text(
                    'Go to screen 1: ',
                    style: TextStyle(
                      fontSize: 20,
                    ),
                  ),
                ),
                ElevatedButton(
                    child: Text('Go'),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => FirstScreen(),
                        ),
                      );
                    }),
              ]),
              Padding(
                padding: EdgeInsets.only(left: 0, top: 50, right: 0, bottom: 0),
                child: Row(children: [
                  Padding(
                    padding:
                    EdgeInsets.only(left: 0, top: 0, right: 70, bottom: 0),
                    child: Text(
                      'Go to screen 2: ',
                      style: TextStyle(
                        fontSize: 20,
                      ),
                    ),
                  ),
                  ElevatedButton(
                      child: Text('Go'),
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => SecondScreen(),
                          ),
                        );
                      }),
                ]),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class FirstScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Padding(
          padding: EdgeInsets.only(left: 50, top: 50, right: 0, bottom: 0),
          child: Column(
            children: [
              Text(
                'Hello from Screen 1',
                style: TextStyle(fontSize: 24.0),
              ),
              ElevatedButton(
                  child: Text('Go Home'),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => Buttons(),
                      ),
                    );
                  }),
            ],
          ),
        ),
      ),
    );
  }
}

class SecondScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Padding(
          padding: EdgeInsets.only(left: 50, top: 50, right: 0, bottom: 0),
          child: Column(
            children: [
              Text(
                'Hello from Screen 2',
                style: TextStyle(fontSize: 24.0),
              ),
              ElevatedButton(
                  child: Text('Go Home'),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => Buttons(),
                      ),
                    );
                  }),
            ],
          ),
        ),
      ),
    );
  }
}
