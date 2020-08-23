import 'package:flutter/material.dart';
import 'package:flutterproject/book/booklistview.dart';
import 'package:flutterproject/book/booktype.dart';

class BookPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return BookPageState();
  }
}

class BookPageState extends State<BookPage>
    with SingleTickerProviderStateMixin {
  TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(initialIndex: 0, length: 3, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      initialIndex: 0,
      length: 3,
      child: Scaffold(
        appBar: AppBar(
          title: Container(
            child: TabBar(
              controller: _tabController,
              isScrollable: false,
              unselectedLabelColor: Colors.black,
              indicatorColor: Color(0xFfff9a6a),
              indicatorSize: TabBarIndicatorSize.label,
              labelColor: Color(0xFfff9a6a),
              labelStyle: TextStyle(fontSize: 18),
              tabs: <Widget>[
                Tab(
                  text: "女生",
                ),
                Tab(
                  text: "漫画",
                ),
                Tab(
                  text: "男生",
                ),
              ],
            ),
          ),
        ),
        body: TabBarView(
          children: [
            BookListWidget(BookType.female),
            BookListWidget(BookType.male),
            BookListWidget(BookType.catroon),
          ],
          controller: _tabController,
        ),
      ),
    );
  }
}
