package Structural;

// Basic Classes to set up...
class Audio {
    public Audio(){}
}

class Images {
    public Images(){}
}

class Video {
    private Audio audio;
    private Images[] images;

    public Video(Audio audio, Images[] images){
        this.audio = audio;
        this.images = images;
    }
}

class Compression {
    private String name;
    public Compression(){
        name = "BasicCompression";
    };
}

// Our Facade is here!, DoComplexWork is doing something that the user could have done manually
// But, it may be more benefical to hide this information and do it in a simple method!
class VideoEditor {
    private Video vid;
    private Compression comp;

    public VideoEditor(Video video, Compression comp){
        this.vid = video;
        this.comp = comp;
    }

    public void DoComplexWork(){
        System.out.println("User doesn't know what is going on here...");
        System.out.println("Calling a lot of complex methods...");
    }
}

public class Facade {
    public static void main(String[] args) {
        // Setup and work from the user's end doesn't change regardless if the methods need to change or not!
        Audio a1 = new Audio();
        Images i1 = new Images();
        Images i2 = new Images();
        Images[] it = {i1, i2};
        Video v1 = new Video(a1, it);
        Compression cmp = new Compression();
        VideoEditor ve = new VideoEditor(v1, cmp);
        ve.DoComplexWork();
    }
}
