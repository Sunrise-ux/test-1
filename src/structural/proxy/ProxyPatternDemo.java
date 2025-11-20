// Subject
interface VideoDownloader {
    Video downloadVideo(String videoId);
}

// Real Subject
class RealVideoDownloader implements VideoDownloader {
    public RealVideoDownloader() {
        System.out.println("RealVideoDownloader initialized - this is expensive!");
    }
    
    @Override
    public Video downloadVideo(String videoId) {
        System.out.println("Downloading video " + videoId + " from server...");
        // Simulate network delay
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        
        return new Video(videoId, "Video content for " + videoId);
    }
}

// Proxy
class VideoDownloaderProxy implements VideoDownloader {
    private RealVideoDownloader realDownloader;
    private Map<String, Video> cache;
    
    public VideoDownloaderProxy() {
        this.cache = new HashMap<>();
    }
    
    @Override
    public Video downloadVideo(String videoId) {
        // Lazy initialization
        if (realDownloader == null) {
            realDownloader = new RealVideoDownloader();
        }
        
        // Check cache first
        if (cache.containsKey(videoId)) {
            System.out.println("Returning cached video: " + videoId);
            return cache.get(videoId);
        }
        
        // Download and cache
        Video video = realDownloader.downloadVideo(videoId);
        cache.put(videoId, video);
        return video;
    }
    
    public void clearCache() {
        cache.clear();
        System.out.println("Cache cleared");
    }
}

// Video class
class Video {
    private String id;
    private String content;
    
    public Video(String id, String content) {
        this.id = id;
        this.content = content;
    }
    
    public String getId() { return id; }
    public String getContent() { return content; }
    
    @Override
    public String toString() {
        return "Video{" + id + ": " + content.substring(0, Math.min(20, content.length())) + "...}";
    }
}

// Main class
public class ProxyPatternDemo {
    public static void main(String[] args) {
        VideoDownloader downloader = new VideoDownloaderProxy();
        
        // First time - downloads
        System.out.println("First download:");
        Video video1 = downloader.downloadVideo("video123");
        System.out.println("Got: " + video1);
        
        // Second time - from cache
        System.out.println("\nSecond download (same video):");
        Video video2 = downloader.downloadVideo("video123");
        System.out.println("Got: " + video2);
        
        // Different video - downloads again
        System.out.println("\nThird download (different video):");
        Video video3 = downloader.downloadVideo("video456");
        System.out.println("Got: " + video3);
    }
}