import 'package:flutter/material.dart';
import 'package:mydobban/screen_utils.dart';
import 'dart:async';
import 'dart:io';
import 'dart:ui';
import 'package:flutter/services.dart';

Color color = Colors.green;
void main() {
  runApp(MyApp());
  if (Platform.isAndroid) {
    SystemUiOverlayStyle style = SystemUiOverlayStyle(statusBarColor: color);
    SystemChrome.setSystemUIOverlayStyle(style);
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return StartWidget(
      child: MaterialApp(
        theme: ThemeData(backgroundColor: Colors.white),
        home: Scaffold(
          body: SplashWidget(),
        ),
      ),
    );
  }
}

class StartWidget extends StatefulWidget {
  final Widget child;

  @override
  _StartWidgetState createState() => _StartWidgetState();

  StartWidget({Key key, @required this.child});
}

class _StartWidgetState extends State<StartWidget> {
  @override
  void initState() {
    super.initState();

  }
  @override
  Widget build(BuildContext context) {
    return Container(
      child: widget.child,
    );

  }
}

class SplashWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      child: Stack(
        children: <Widget>[

          Align(
            alignment: Alignment.topRight,
            child: Container(
              child: CountDownTimer(),
//              padding: EdgeInsets.fromLTRB(0, 0, 20, 0),
              margin: EdgeInsets.fromLTRB(0, 50, 20, 0),
              decoration: BoxDecoration(
                  color: Color(0xffEDEDED),
                  borderRadius: const BorderRadius.all(Radius.circular(10.0))),
              padding: EdgeInsets.fromLTRB(10, 10, 10, 10),
            ),
          ),
          Align(
            alignment: Alignment(0, 0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                CircleAvatar(
                  radius: ScreenUtils.screenW(context) / 3,
                  backgroundColor: Colors.white,
                  backgroundImage: AssetImage('images/home.png'),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 20.0),
                  child: Text(
                    '落花有意随流水,流水无心恋落花',
                    style: TextStyle(fontSize: 15.0, color: Colors.black),
                  ),
                )
              ],
            ),
          ),
          Align(
            alignment: Alignment.bottomCenter,
            child: GestureDetector(
              onTap: (){
                color=Colors.green;

              },
              child: Padding(
                padding: const EdgeInsets.only(bottom: 40),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Image.asset('images/ic_launcher.png', width: 50, height: 50),
                    Padding(
                      padding: EdgeInsets.only(left: 10),
                      child: Text(
                        '豆芽',
                        style: TextStyle(color: Colors.green, fontSize: 30),
                      ),
                    )
                  ],
                ),
            ),
            ),
          )
        ],
      ),
    );
  }
}

class CountDownTimer extends StatefulWidget {
  @override
  _CountDownTimerState createState() => _CountDownTimerState();
}

class _CountDownTimerState extends State<CountDownTimer> {
  var second = 5;
  Timer timer;

  @override
  void initState() {
    super.initState();
    _startTimer();
  }

  void _startTimer() {
    timer = Timer.periodic(Duration(seconds: 1), (timer) {
      setState(() {});
      if (second < 1) {
        timer.cancel();
        return;
      }
      second--;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Text(
      '$second',
      style: TextStyle(fontSize: 17, color: Colors.black),
    );
  }
}
