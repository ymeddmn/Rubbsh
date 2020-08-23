class WuldDto {
  String cover;
  String title;
  String description;

  WuldDto.fromJson(Map map) {
    cover = map['cover'];
    title = map['title'];
    description = map['description'];
  }
}
