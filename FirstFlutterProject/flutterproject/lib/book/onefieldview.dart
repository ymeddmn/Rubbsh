import 'package:flutter/material.dart';
import 'package:flutterproject/book/bookitemcellwidget.dart';
import 'package:flutterproject/model/bookmodule.dart';
import 'package:flutterproject/utils/screen.dart';

class OneFieldWidget extends StatelessWidget {
  BookModule bookModule;

  OneFieldWidget(this.bookModule);

  getItem(BookItem it) {
    return Container(
      child: Text('一横一田的item'),
    );
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> children = [];

    var books = bookModule.books;
    books.sublist(0, 1).forEach((it) {
      children.add(CellOneHorWidget(it));
    });
    books.sublist(1).forEach((it) {
      children.add(CellHorizontalWidget(it));
    });
    var width = Screen.width - 15 * 2;
    return ClipRRect(
      borderRadius: BorderRadius.circular(8),
      child: Container(
        child: Column(
          children: <Widget>[
            CellTitleWidget(bookModule.name),
            Container(
              width: width,
              child: Wrap(
                children: children.toList(),
                spacing: 15,
                runSpacing: 8,
              ),
            ),
            SizedBox(height: 8,)
          ],
        ),
      ),
    );
  }
}
