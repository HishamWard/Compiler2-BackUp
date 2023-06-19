class c {
  String qaq;
  void a(){
    //qaq = "a";
    //qq = "a";
  }
  double func(String qqqq){
    String aaa = "a";
    //aaa = 3;
  }
  String adasqq = "aaa";

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        MaterialApp(
          home: FirstScreen(),
          title: 'Navigation Demo',
        ),
        Scaffold(
            body: ElevatedButton(
                child: Text('1'),
                onPressed: () {
                  //String qqadasqq = "aaa";
                  Padding(
                    child: Text('11'),
                    padding: EdgeInsets.only(
                        left: 1.5, top: 3, right: 1.5, bottom: 6),
                  );
                  TextField(
                    controller: _textController,
                  );
                  // Navigator.push(
                  //   context,
                  //   MaterialPageRoute(
                  //     builder: (context) => SecondScreen('name'),
                  //   ),
                  // );
                })),
        Container(
            child: Text('Go to Second Screen',
                style: TextStyle(color: Colors.grey, fontSize: 40.0)),
            height: 200,
            margin: EdgeInsets.only(left: 1.5, top: 3, right: 1.5, bottom: 6)),
        Column(
          children: [],
        ),
      ],
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.center,
    );
  }
}