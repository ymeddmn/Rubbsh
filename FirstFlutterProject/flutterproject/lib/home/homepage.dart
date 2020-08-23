import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutterproject/api/request.dart';
import 'package:flutterproject/home/bannerwidget.dart';
import 'package:flutterproject/home/dailytuijiam.dart';
import 'package:flutterproject/home/updatetoday.dart';
import 'package:flutterproject/home/wuliangrecommand.dart';
import 'package:flutterproject/model/dailytuimodel.dart';
import 'package:flutterproject/model/homemodel.dart';
import 'package:flutterproject/model/updatetoday.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomePageState();
  }
}

class HomePageState extends State<HomePage> with AutomaticKeepAliveClientMixin{
  List<String> _banner = [];
  List<WuldDto> _wuldList = [];
  DailyTuiDto dailyTuiDto;

  /// 今日我更新
  List<UpdateToday> updateTodayList = [];

  ScrollController _scroller = ScrollController();
  double alpha = 0;
  double titleHeight=100;
  @override
  void initState() {
    super.initState();
    fetchData();
    _scroller.addListener(() {
      var offset = _scroller.offset;
      if (offset > 0 && offset < titleHeight) {
        alpha = (offset)/titleHeight;
      } else if (offset >titleHeight) {
        alpha=1;
      } else {
        alpha=0;
      }
//      print('偏移：$alpha+$offset');
      if(alpha!=1){
        setState(() {
          alpha=alpha;
        });
      }
    });
  }

  @override
  void dispose() {
    super.dispose();
    _scroller.dispose();
  }

  Widget _buildItem(BuildContext context, int index) {
    Widget widget;
    //获得item
    switch (index) {
      case 0:
        widget = BannerWidget(_banner);
        break;
      case 1:
        widget = WuLdRecommandWidget(_wuldList);
        break;
      case 2:
        widget = DailyTuijmWidget(dailyTuiDto);
        break;
      case 3:
        widget = UpdateTodayWidget(updateTodayList);
        break;
    }
    return widget;
  }

  Future<void> fetchData() async {
    var data = await Request.get(url: 'home_comic');
    _banner.clear();
    data['banner'].forEach((it) {
      _banner.add(it);
    });
    _wuldList.clear();
    data['blockList'].forEach((it) {
      _wuldList.add(WuldDto.fromJson(it));
    });
    updateTodayList.clear();
    data["updateTodayList"].forEach((data) {
      updateTodayList.add(UpdateToday.fromJson(data));
    });
    dailyTuiDto = DailyTuiDto.fromJson(data["recommendEveryDay"]);
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: <Widget>[
          RefreshIndicator(
            onRefresh: _onRefresh,
            child: ListView.builder(
              itemBuilder: (context, index) {
                return _buildItem(context, index);
              },
              itemCount: 4,
              controller: _scroller,
            ),
          ),
          Opacity(
            opacity: alpha,
            child: Container(
              height: 50,
              color: Colors.green,
            ),
          ),
        ],
      ),
    );
  }

  Future<void> _onRefresh() async {
    await Future.delayed(Duration(milliseconds: 3000), () {
      //模拟三秒网络请求，三秒完成后刷新的进度条自动回弹
    });
    fetchData();
  }

  @override
  // TODO: implement wantKeepAlive
  bool get wantKeepAlive => true;
}
