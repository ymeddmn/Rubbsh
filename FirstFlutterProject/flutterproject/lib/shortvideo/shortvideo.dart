import 'package:flutter/material.dart';

class ShortVideoPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return ShortVideoPageState();
  }
}

class ShortVideoPageState extends State<ShortVideoPage> {
  List<String> urlList = [
    'https://wx3.sinaimg.cn/crop.0.0.604.339.360/006QmDx6ly1g2ia60z17lj30gs0b8q5u.jpg',
    'https://wx4.sinaimg.cn/crop.0.0.604.339.360/006QmDx6ly1g2bhtbu3s3j30gs0b8wh9.jpg',
    'https://wx4.sinaimg.cn/crop.0.0.1920.1080.360/6e348924ly1g2is9g114lj21hc0u0hdt.jpg',
    'https://wx3.sinaimg.cn/crop.0.0.1920.1080.360/76e1e855ly1g2jn1ubnm8j21hc0u0gxh.jpg',
    'https://wx1.sinaimg.cn/crop.0.40.960.540.360/006UjVSbly1g2g7t2o5uqj30qo0gon9l.jpg',
    'https://wx4.sinaimg.cn/crop.0.0.1920.1080.360/50214f26ly1g2cqv4vnzrj21hc0u0b29.jpg',
    'https://wx1.sinaimg.cn/crop.0.0.1600.900.360/007mU3qOly1g0n2wq3k2rj318g0p07vp.jpg',
    'https://wx2.sinaimg.cn/crop.0.0.1600.900.360/007mU3qOly1g0eo03wasuj318g0p01kx.jpg',
    'https://wx3.sinaimg.cn/crop.0.0.1600.900.360/007mU3qOly1g06ddkzjgyj318g0p0e81.jpg',
    'https://wx3.sinaimg.cn/crop.0.0.1600.900.360/007mU3qOly1fzydecaeslj318g0p01kx.jpg',
    'https://wx4.sinaimg.cn/crop.0.0.1920.1080.360/76e1e855ly1g13lvk5imsj21hc0u0hdt.jpg',
    'https://wx3.sinaimg.cn/crop.0.0.1920.1080.360/76e1e855ly1g0qxcn13mij21hc0u01kx.jpg',
    'https://wx2.sinaimg.cn/crop.0.139.1053.592.360/006qdyzsly1g1g349c25lj30t90o9tia.jpg',
    'https://wx4.sinaimg.cn/crop.0.75.918.516.360/006qdyzsly1g186z2ch9bj30pi0ilncz.jpg',
  ];

  @override
  void initState() {
    super.initState();
  }

  Widget buildItem(String url) {
    Widget widget = GestureDetector(
      child: ClipRRect(
        borderRadius: BorderRadius.circular(7),
        child: Container(
          color: Colors.white,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Image.network(url),
              SizedBox(
                height: 5,
              ),
              Container(
                margin: EdgeInsets.symmetric(horizontal: 5),
                child: Text(
                  "#味里故乡#王晓晨 匡牧野山东篇：红瓦绿树间的文艺味道,红瓦绿树间的文艺味道",
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  style: TextStyle(
                      fontSize: 15,
                      color: Colors.black,
                      decoration: TextDecoration.none),
                ),
              ),
              SizedBox(
                height: 5,
              ),
              Container(
                alignment: Alignment.center,
                margin: EdgeInsets.symmetric(horizontal: 5),
                child: Row(
                  children: <Widget>[
                    Image.asset(
                      'img/play_video.png',
                      color: Colors.grey,
                      width: 15,
                      height: 15,
                    ),
                    RichText(
                      text: TextSpan(children: <TextSpan>[
                        TextSpan(
                            text: '1.3万',
                            style: TextStyle(color: Colors.yellow)),
                        TextSpan(
                            text: '播放', style: TextStyle(color: Colors.grey)),
                      ]),
                    ),
                    Expanded(
                      child: Container(),
                    ),
                    Text('1305赞')
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
    return widget;
  }

  @override
  Widget build(BuildContext context) {
    var children = urlList.map((url) => buildItem(url)).toList();
    return Scaffold(
      body: Container(
        padding: EdgeInsets.fromLTRB(8, 0, 8, 0),
        child: GridView(
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 2,
              mainAxisSpacing: 8,
              crossAxisSpacing: 8),
          children: children,
        ),
      ),
    );
  }
}
