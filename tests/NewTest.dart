class TextFields extends StatelessWidget {
  TextEditingController firstController = TextEditingController();
  TextEditingController secondController = TextEditingController();
  double test1 = 5;
  String test2 = "Colors.Red";

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
                  style: TextStyle(fontSize: 30, color: test2,),
                ),
              ),
              Form(
                child: Row(children: [
                  Text(
                    test1,
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
                            labelText: "FirstField"
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
                                FirstScreen(name: test2),
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
                      test2,
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
                            labelText: "SecondField"
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
  String name = "placeholder";

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
                name,
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
