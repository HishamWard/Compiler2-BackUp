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
          padding: EdgeInsets.only(left: 100, top: 100, right: 100, bottom: 20),
          child: Column(
            children: [
              Row(children: [
                Container(
                  child: Column(
                      children: [
                        Container(
                          child: Text('', style: TextStyle(
                            color: Colors.black,
                          ),),
                          width: 100,
                          height: 30,
                          color: Colors.Black,
                          margin: EdgeInsets.only(
                              left: 0, top: 20, right: 50, bottom: 0),),
                        Container(
                          child: Text('', style: TextStyle(
                            color: Colors.black,
                          ),),
                          width: 150,
                          height: 30,
                          color: Colors.Black,
                          margin: EdgeInsets.only(
                              left: 0, top: 20, right: 0, bottom: 0),),
                        Container(
                          child: Text('', style: TextStyle(
                            color: Colors.black,
                          ),),
                          width: 150,
                          height: 30,
                          color: Colors.Black,
                          margin: EdgeInsets.only(
                              left: 0, top: 20, right: 0, bottom: 0),),
                      ]
                  ),
                  width: 200,
                  height: 200,
                  margin: EdgeInsets.only(
                      left: 0, top: 0, right: 50, bottom: 0),
                ),
                Padding(
                  padding:
                  EdgeInsets.only(left: 0, top: 0, right: 70, bottom: 0),
                  child: Text(
                    'Go to screen 1: ',
                    style: TextStyle(
                      fontSize: 20,
                      color: Colors.black,
                    ),
                  ),
                ),
                ElevatedButton(
                    child: Text('Go', style: TextStyle(
                      color: Colors.black,
                    ),),
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
                  Container(
                    child: Column(
                        children: [
                          Container(
                            child: Text('', style: TextStyle(
                              color: Colors.black,
                            ),),
                            width: 100,
                            height: 30,
                            color: Colors.Black,
                            margin: EdgeInsets.only(
                                left: 0, top: 20, right: 50, bottom: 0),),
                          Container(
                            child: Text('', style: TextStyle(
                              color: Colors.black,
                            ),),
                            width: 150,
                            height: 30,
                            color: Colors.Black,
                            margin: EdgeInsets.only(
                                left: 0, top: 20, right: 0, bottom: 0),),
                          Container(
                            child: Text('', style: TextStyle(
                              color: Colors.black,
                            ),),
                            width: 150,
                            height: 30,
                            color: Colors.Black,
                            margin: EdgeInsets.only(
                                left: 0, top: 20, right: 0, bottom: 0),),
                        ]
                    ),
                    width: 200,
                    height: 200,
                    margin: EdgeInsets.only(
                        left: 0, top: 0, right: 50, bottom: 0),
                  ),
                  Padding(
                    padding:
                    EdgeInsets.only(left: 0, top: 0, right: 70, bottom: 0),
                    child: Text(
                      'Go to screen 2: ',
                      style: TextStyle(
                        fontSize: 20,
                        color: Colors.black,
                      ),
                    ),
                  ),
                  ElevatedButton(
                      child: Text('Go', style: TextStyle(
                        color: Colors.black,
                      ),),
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
