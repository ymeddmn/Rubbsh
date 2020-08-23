import 'package:flutter/material.dart';

import '../ty_color.dart';

class MePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MePageState();
  }
}

class MePageState extends State<MePage> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView(
        children: <Widget>[MeHeaderWidget(), MeListWidget()],
      ),
    );
  }
}

/**
 * 头部
 */
class MeHeaderWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(vertical: 20),
      child: Row(
        children: <Widget>[
          Container(
            child: Image.asset('img/placeholder_avatar.png'),
            width: 80,
            height: 80,
          ),
          Expanded(
            child: Container(
              margin: EdgeInsets.only(right: 40),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: <Widget>[
                  buildItem('0.0', '书豆余额'),
                  buildItem('0', '书券（张）'),
                  buildItem('0', '月票'),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  buildItem(String var1, String var2) {
    return Column(
      children: <Widget>[
        Text(
          var1,
          style: TextStyle(fontWeight: FontWeight.bold,fontSize: 18),
        ),
        SizedBox(
          height: 6,
        ),
        Text(var2)
      ],
    );
  }
}

class MeListWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: <Widget>[
          MeItemWidget('img/me_wallet.png', '钱包'),
          MeItemWidget('img/me_record.png', '消费充值记录'),
          MeItemWidget('img/me_buy.png', '购买的书'),
          MeItemWidget('img/me_vip.png', '我的会员'),
          MeItemWidget('img/me_coupon.png', 'Bloc示例'),
          MeItemWidget('img/me_favorite.png', 'Stream Api'),
          MeItemWidget('img/me_action.png', 'EventChannel'),
          MeItemWidget('img/me_theme.png', '自定义Widget'),
          MeItemWidget('img/me_record.png', '加载更多示例'),
          MeItemWidget('img/me_date.png', '地图'),
          MeItemWidget('img/me_comment.png', '关于'),
          MeItemWidget('img/reader_battery.png', '电量'),
          MeItemWidget('img/me_setting.png', '设置'),
          MeItemWidget('img/me_feedback.png', 'Girhub 网页'),
        ],
      ),
    );
  }
}

class MeItemWidget extends StatelessWidget {
  String icon;
  String text;

  MeItemWidget(this.icon, this.text);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 15),
      alignment: Alignment.center,
      height: 60,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Expanded(
            child: Row(
              children: <Widget>[
                Container(
                  child: Image.asset(icon),
                  margin: EdgeInsets.only(right: 20),
                ),
                Expanded(
                  child: Text(text),
                ),
                Container(
                  child: Image.asset('img/arrow_right.png'),
                )
              ],
            ),
          ),
          Container(
            height: 2,
            color: TYColor.lightGray,
            margin: EdgeInsets.only(left: 40),
          ),
        ],
      ),
    );
  }
}
