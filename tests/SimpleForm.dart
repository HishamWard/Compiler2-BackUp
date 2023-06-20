import 'package:flutter/material.dart';

void main() {
  runApp( MyApp());
}

class SimpleForm1 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Form(
        child: Column(
          children: [
            Row(
              children: [
                Text("First Name"),
                TextField(decoration: InputDecoration(labelText: "FirstName")),
              ],
            ),
            Row(
              children: [
                Text("Last Name"),
                TextField(decoration: InputDecoration(labelText: "LastName")),
              ],
            ),

            Row(
              children: [
                Text("Age"),
                TextField(decoration: InputDecoration(labelText: "Age")),
              ],
            ),

            Row(
              children: [
                Text("Work"),
                TextField(decoration: InputDecoration(labelText: "Work")),
              ],
              mainAxisAlignment: MainAxisAlignment.space-between
            ),

            ElevatedButton(
                child: Text('Submit Form'),
                onPressed: () {
                    Navigator.push(context, MaterialPageRoute(builder: (context) =>
                        IyadIsHot(name: 'hasan'),),);
                 }
             ),
          ],
        )
    );
  }
}

class SimpleForm2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
        children: [
          Text('Thank you!'),
        ]
    );
  }
}

