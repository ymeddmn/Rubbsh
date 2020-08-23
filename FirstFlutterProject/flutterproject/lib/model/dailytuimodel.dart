class DailyTuiDto {
  String cover;
  String title;
  String author;

  DailyTuiDto.fromJson(Map data) {
    cover = data['cover'];
    title = data['title'];
    author = data['author'];
  }
}
