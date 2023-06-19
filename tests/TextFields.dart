import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'TextFields',
      home: TextFields(),
    );
  }
}

class TextFields extends StatelessWidget {
  TextEditingController firstController = TextEditingController();
  TextEditingController secondController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.white,
        child: Padding(
          padding: EdgeInsets.only(left: 90, top: 100, right: 0, bottom: 0),
          child: Column(
            children: [
              Padding(
                padding: EdgeInsets.only(left: 0, top: 0, right: 0, bottom: 30),
                child: Text(
                  'TextFields',
                  style: TextStyle(fontSize: 30, color: Colors.black,),
                ),
              ),
              Form(
                child: Row(children: [
                  Text(
                    'Text 1: ',
                    style: TextStyle(
                      fontSize: 20,
                      color: Colors.Black,
                    ),
                  ),
                  Padding(
                    padding: EdgeInsets.only(
                        left: 5, top: 0, right: 5, bottom: 0),
                    child: TextField(
                      controller: firstController,
                      decoration: InputDecoration(
                        labelText: "FirstFieldByIyadoooooo"
                      )
                    ),),
                  ElevatedButton(
                      child: Text('Go', style: TextStyle(
                        color: Colors.Black,
                      ),),
                      onPressed: () {
                        dynamic textFieldParameter = firstController.text;
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) =>
                                FirstScreen(name: "textFieldParameter"),
                          ),
                        );
                      }),
                ]),
              ),
              Padding(
                padding: EdgeInsets.only(left: 0, top: 60, right: 0, bottom: 0),
                child: Form(
                  child: Row(children: [
                    Text(
                      'Text 2: ',
                      style: TextStyle(
                        fontSize: 20,
                        color: Colors.Black,
                      ),
                    ),
                    Padding(
                      padding: EdgeInsets.only(
                          left: 5, top: 0, right: 5, bottom: 0),
                      child: TextField(
                        controller: secondController,
                        decoration: InputDecoration(
                          labelText: "SecondFieldByRedwanoooo"
                        ),
                      ),),
                    ElevatedButton(
                        child: Text('Go', style: TextStyle(
                          color: Colors.Black,
                        ),),
                        onPressed: () {
                          dynamic textFieldParameter = secondController.text;
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) =>
                                  SecondScreen(name: "textFieldParameter"),
                            ),
                          );
                        }),
                  ]),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class FirstScreen extends StatelessWidget {
  String name = "";

  FirstScreen(this.name);

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
                        builder: (context) => TextFields(),
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
  String name2 = "";

  SecondScreen(this.name2);

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
                        builder: (context) => TextFields(),
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
