import MusicTrackExercise.MusicSupplier;
import MusicTrackExercise.Track;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MusicSupplier musicSupplier = new MusicSupplier();
        System.out.println(musicSupplier);
        Track track= musicSupplier.getTrackByName("Love on Top");
        System.out.println("Get track by name: \n"+track);
        List<Track> allBandTracks = musicSupplier.getAllBandTracks("Beyonce");
        System.out.println("All band tracks: \n"+allBandTracks);
        int lengthByArtist = musicSupplier.getTotalTracksLength("Don McLean");
        System.out.println("Length by artist: "+lengthByArtist);
        List<Track> allTracksBelowPrice = musicSupplier.getAllTracksBelowPrice(379);
        System.out.println("All tracks below price 379: \n"+allTracksBelowPrice);
    }

}
