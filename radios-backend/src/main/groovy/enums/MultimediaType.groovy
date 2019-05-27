package enums

enum MultimediaType {
    AUDIO(['mp3']), IMAGE(['jpg', 'jpeg', 'png', 'gif']), VIDEO(['mp4'])

    List<String> types

    MultimediaType(List<String> list) {
        this.types = list
    }
}
