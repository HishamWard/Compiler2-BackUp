import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Navigation Demo',
      home: FirstScreen(),
    )
  }
}

class FirstScreen extends StatelessWidget {
  TextEditingController _textController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        margin: EdgeInsets.only(left: 1000.0, top: 100.0, right: 0.0, bottom: 0.0),
        width: 500,
        height: 500,
        child: Column(
          children: [
            TextField(
              controller: _textController,
            ),
            ElevatedButton(
              child: Text('Go to Second Screen'),
              onPressed: () {
                // String name = _textController.text;
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => SecondScreen(name: 'name'),
                  ),
                );
              },
            ),
          ],
        ),
      ),
    )
  }
}

class SecondScreen extends StatelessWidget {
  String name = '';

  SecondScreen(this.name);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Text(
          'Hello, $name',
          style: TextStyle(fontSize: 24.0),
        ),
      ),
    )
  }
}
