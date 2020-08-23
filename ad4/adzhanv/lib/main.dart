import 'package:adzhanv/main1.dart';
import 'package:adzhanv/screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_seekbar/flutter_seekbar.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  SystemChrome.setPreferredOrientations(
      [DeviceOrientation.landscapeLeft, DeviceOrientation.landscapeRight]);

  runApp(FirstStatelessWidget());
}

class FirstStatelessWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: FirstPage(),
    );
  }
}

class FirstPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return FirstState();
  }
}

class FirstState extends State<FirstPage> {
  TextEditingController _controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('设置标题'),
      ),
      body: Container(
        margin: EdgeInsets.fromLTRB(30, 20, 30, 30),
        width: double.infinity,
        child: ListView(
          children: <Widget>[
            Column(
              children: <Widget>[
                TextField(
                  controller: _controller,
                  style: TextStyle(color: Colors.white),
                  decoration: InputDecoration(
                      enabledBorder: enableBorder(),
                      focusedBorder: enableFocusBorder(),
                      fillColor: Colors.pink,
                      hintText: '设置滚动广告内容',
                      filled: true),
                ),
                RaisedButton(
                  child: Text('点击保存广告文案'),
                  onPressed: () {
                    setData(_controller.text);
                  },
                ),
                RaisedButton(
                  child: Text('点击进入轮播广告页面'),
                  onPressed: () {
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) => MyApp()));
                  },
                )
              ],
            )
          ],
        ),
      ),
    );
  }

  Future setData(String data) async {
    var share = await SharedPreferences.getInstance();
    share.setString("data", data);
  }

  enableBorder() {
    return OutlineInputBorder(
        borderRadius: BorderRadius.circular(10),
        borderSide: BorderSide(color: Colors.yellow, style: BorderStyle.solid));

  }

  enableFocusBorder() {
    return OutlineInputBorder(
        borderRadius: BorderRadius.circular(10),
        borderSide: BorderSide(color: Colors.red, style: BorderStyle.solid));
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> with TickerProviderStateMixin {
  double size = 100;

  var text = '';
  double speed = 1;

  @override
  Future initState()  {
    super.initState();
    getData();
    size = Screen.width;
  }

  Future getData() async {
    final share = await SharedPreferences.getInstance();
    var data = share.getString('data');
    setState(() {
      text = data;
    });
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setEnabledSystemUIOverlays([SystemUiOverlay.bottom]);
    return Scaffold(
      body: Center(
        child: Container(
          color: Colors.red,
          child: YYMarquee(
              new Text(
                text,
                style: TextStyle(
                    fontSize: (Screen.height - 100), color: Colors.orange),
              ),
              200.0,
              new Duration(seconds: 1),
              230.0),
        ),
      ),
    );
  }
}
