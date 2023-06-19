import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Fruits',
      home: Fruits(),
    );
  }
}

class Fruits extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.silver,
        margin: EdgeInsets.only(left: 100, top: 70, right: 100, bottom: 0),
        child: Padding(
          padding: EdgeInsets.only(left: 0, top: 20, right: 0, bottom: 20),
          child: Column(
            children: [
              Row(children: [
                Text(
                  "Fruits",
                  style: TextStyle(
                    fontSize: 50,
                    fontWeight: 700,
                    color: Colors.white,
                  ),
                ),
              ]),
              Padding(
                padding:
                EdgeInsets.only(left: 0, top: 40, right: 0, bottom: 30),
                child: Row(children: [
                  Container(
                      color: Colors.gray,
                      padding: EdgeInsets.only(
                          left: 10, top: 10, right: 10, bottom: 10),
                      child: Text(
                        "Banana",
                        style: TextStyle(
                          fontSize: 25,
                          color: Colors.yellow,
                        ),
                      ),
                  ),
                  Padding(
                      padding:
                      EdgeInsets.only(left: 100, top: 0, right: 100, bottom: 0),
                      child: Container(
                    color: Colors.gray,
                    padding: EdgeInsets.only(
                        left: 10, top: 10, right: 10, bottom: 10),
                    child: Text(
                      "Apple",
                      style: TextStyle(
                        fontSize: 25,
                        color: Colors.red,
                      ),
                    ),),),
                  Container(
                    color: Colors.gray,
                    padding: EdgeInsets.only(
                        left: 10, top: 10, right: 10, bottom: 10),
                    child: Text(
                      "Lemon",
                      style: TextStyle(
                        fontSize: 25,
                        color: Colors.yellow,
                      ),
                    ),
                  ),
                ]),
              ),
              Row(
                children: [
                  Padding(
                    padding:
                    EdgeInsets.only(left: 0, top: 0, right: 50, bottom: 0),
                    child: Container(
                      color: Colors.gray,
                      padding: EdgeInsets.only(
                          left: 10, top: 10, right: 10, bottom: 10),
                      child: Text(
                        "Watermelon",
                        style: TextStyle(
                          fontSize: 25,
                          color: Colors.lime,
                        ),
                      ),),
                  ),
                  Container(
                    color: Colors.gray,
                    padding: EdgeInsets.only(
                        left: 10, top: 10, right: 10, bottom: 10),
                    child: Text(
                      "StrawBerry",
                      style: TextStyle(
                        fontSize: 25,
                        color: Colors.maroon,
                      ),
                    ),),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
