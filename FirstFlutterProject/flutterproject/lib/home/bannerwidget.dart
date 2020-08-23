import 'dart:math';

import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:flutterproject/utils/screen.dart';

import '../ty_color.dart';

/**
 * 首页顶部轮播
 */
class BannerWidget extends StatefulWidget {
  List<String> banners;

  BannerWidget(this.banners);

  @override
  State<StatefulWidget> createState() {
    return BannerWidgetState();
  }
}

class BannerWidgetState extends State<BannerWidget>
    with AutomaticKeepAliveClientMixin {
  int currentIndex = 0;

  @override
  Widget build(BuildContext context) {
    if (widget.banners.length < 1) {
      return SizedBox();
    }
    var width = Screen.width;
    return GestureDetector(
      onTap: (){

      },
      child: Stack(
        children: <Widget>[
          Container(
            child: CarouselSlider(
              viewportFraction: 1.0,
              aspectRatio: 2.0,
              autoPlay: true,
              pauseAutoPlayOnTouch: Duration(milliseconds: 2000),
              enlargeCenterPage: false,
              items: map<Widget>(widget.banners, (index, url) {
                return Image.network(
                  url,
                  width: width,
//                height: 0.618 * width,
                  fit: BoxFit.cover,
                );
              }),
              onPageChanged: (index) {
                setState(() {
                  currentIndex = index;
                });
              },
            ),
          ),
          Positioned(
              right: 5,
              bottom: 5,
              child: Row(
                children: map<Widget>(widget.banners, (index, url) {
                  if (currentIndex == index) {
                    return Container(
                      child: Container(
                        width: 12,
                        height: 6,
                        margin:
                            EdgeInsets.symmetric(vertical: 8, horizontal: 3),
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.all(Radius.circular(3)),
                            color: TYColor.primary),
                      ),
                    );
                  } else {
                    return Container(
                      child: Container(
                        width: 6,
                        height: 6,
                        margin:
                            EdgeInsets.symmetric(vertical: 8, horizontal: 3),
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.all(Radius.circular(3)),
                            color: Color(0xffe1e1e1)),
                      ),
                    );
                  }
                }),
              ))
        ],
      ),
    );
  }

  List<T> map<T>(List list, Function handler) {
    List<T> result = [];
    for (int i = 0; i < list.length; i++) {
      result.add(handler(i, list[i]));
    }
    return result;
  }

  @override
  bool get wantKeepAlive => true;
}
