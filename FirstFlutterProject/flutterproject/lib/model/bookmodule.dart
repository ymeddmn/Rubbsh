class BookModule {
  var style;
  var name;
  var id;
  List<BookItem> books = [];

  BookModule.fromJson(Map data) {
    style = data['m_s_style'];
    name = data['m_s_name'];
    id = data['id'];
    List content = data['content'];
    content.forEach((it) {
      books.add(BookItem.fromJson(it));
    });
  }
}

class BookItem {
  var name;
  var imgUrl; //每个item的主图
  var bookName; //书的名字
  var author; //作者名字
  var introduce; //介绍
  var state; //作品状态
  BookItem.fromJson(Map data) {
    name = data['m_s_name'];
    imgUrl = data['book_cover'];
    bookName = data['bookname'];
    author = data['author_name'];
    introduce = data['introduction'];
    state = data['stat_name'];
  }
}
