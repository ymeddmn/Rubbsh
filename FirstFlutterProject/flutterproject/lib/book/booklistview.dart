import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutterproject/api/request.dart';
import 'package:flutterproject/book/FourTwoWidget.dart';
import 'package:flutterproject/book/booktype.dart';
import 'package:flutterproject/book/fourfield.dart';
import 'package:flutterproject/book/onefieldview.dart';
import 'package:flutterproject/book/onefourview.dart';
import 'package:flutterproject/home/bannerwidget.dart';
import 'package:flutterproject/model/bookmodule.dart';

class BookListWidget extends StatefulWidget {
  BookType bookType;

  BookListWidget(this.bookType);

  @override
  State<StatefulWidget> createState() {
    return BookListState();
  }
}

class BookListState extends State<BookListWidget> {
  List<String> urls = [
    "https://manhua.qpic.cn/operation/0/19_23_51_3fa71e4fd07f0f370af0465faa6ccdb5_1555689063579.jpg/0",
    "https://manhua.qpic.cn/operation/0/19_23_51_898a3baa00cba550d9fe64a372bee24a_1555689101098.jpg/0",
    "https://manhua.qpic.cn/operation/0/19_23_52_6234abed1062e3601cd639fd376760a3_1555689133306.jpg/0",
    "https://manhua.qpic.cn/operation/0/19_23_52_c0343c40a42861d776eb2b265015bef2_1555689169278.jpg/0",
    "https://manhua.qpic.cn/operation/0/19_23_53_8968d75fb1619aa30adfaf271a271642_1555689199834.jpg/0"
  ];

  List<BookModule> modules = [];

  @override
  void initState() {
    super.initState();
    _fetchData();
  }

  Future<void> _fetchData() async {
    var action;
    switch (widget.bookType) {
      case BookType.female:
        action = 'home_female';
        break;
      case BookType.male:
        action = 'home_male';
        break;
      case BookType.catroon:
        action = 'home_cartoon';
        break;
    }
    var response = await Request.get(url: action);
    List moduleData = response['module'];
    List<BookModule> modules1 = [];
    moduleData.forEach((it) {
      modules1.add(BookModule.fromJson(it));
    });
    setState(() {
      this.modules = modules1;
    });
  }

  Widget buildItem(BuildContext context, int index) {
    if (modules == null || modules.length == 0) {
      return Container();
    }
    print('数组长度：$modules.length');
    var data = modules[index];
    Widget widget;
    if (index == 0) {
      widget = BannerWidget(urls);
    } else {
      switch (data.style) {
        case 1: //一行四个，一共两行
          widget = FourTwoWidget(data);
          break;
        case 2: //上面横着四个，下面田字格
          widget = FourFieldWidget(data);
          break;
        case 3: //上面横着的一条、下面的田字格
          widget = OneFieldWidget(data);
          break;
        case 4: //一行一个，一共四行
          widget = OneFourWidget(data);
          break;
      }
    }
    if (widget == null) {
      widget = Container();
    }
    return widget;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 8),
      child: RefreshIndicator(
        onRefresh: _fetchData,
        child: ListView.builder(
          itemBuilder: (BuildContext context, int index) {
            return buildItem(context, index);
          },
          itemCount: modules.length,
        ),
      ),
    );
  }
}
