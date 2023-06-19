import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
        children: [
          Container(child: Text(
              'Test', style: TextStyle(fontSize: 40, color: Colors.Green)),
            color: Colors.White,
          ),
          Text('Meow'),
          Text('Redwn', style: TextStyle(
              fontSize: 40, fontWeight: 700, color: Colors.Red)),
          ElevatedButton(
              child: Text('Go to Second Screen'),
              onPressed: () {
                print('meowmeow');
                Navigator.push(context, MaterialPageRoute(builder: (context) =>
                    RedwnMeowMeow(name: 'hasan',
                        age: 20,
                        batata: true,
                        shawerma: true,
                        falafel: true),),);
              }
          ),

        ]
    );
  }
}

class RedwnMeowMeow extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
        children: [
          Text('Yaman'),
          Text('Snow'),
          Text('IYADD'),
        ]
    );
  }
}

