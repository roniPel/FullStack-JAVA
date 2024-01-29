package MusicTrackExercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MusicSupplier {

    private ArrayList<Track> tracks;

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public MusicSupplier() {
        tracks = new ArrayList<>(Arrays.asList( new Track("American Pie","Don McLean",355.32,433),
                                            new Track("Love on Top","Beyonce",446.65,267),
                                            new Track("If I were a boy","Beyonce",287.12,249),
                                            new Track("Irreplaceable","Beyonce",380.00,227),
                                            new Track("Vincent","Don McLean",246.72,235) ) );
    }
    public MusicSupplier(List<Track> tracks) {
        this.tracks = (ArrayList<Track>) tracks;
    }

    public Track getTrackByName(String name) {
        Track findTrack = null;
        for(Track t: getTracks()) {
            if( t.getName().equals(name) ) {
                findTrack = t;
            }
        }
        return findTrack;
    }
    public List<Track> getAllTracksBelowPrice(double price) {
        List<Track> tracksBelowPrice = new ArrayList<>();;
        for(Track t: getTracks()) {
            if(t.getPrice() < price) {
                tracksBelowPrice.add(t);
            }
        }
        return tracksBelowPrice;
    }
    public List<Track> getAllBandTracks (String bandName) {
        List<Track> tracksByBandName = new ArrayList<>();
        for(Track t: getTracks()) {
            if( t.getBandName().equals(bandName) ) {
                tracksByBandName.add(t);
            }
        }
        return tracksByBandName;
    }
    public int getTotalTracksLength (String bandName) {
        int totalTracksLength = 0;
        for(Track t: getTracks()) {
            if( t.getBandName().equals(bandName) ) {
                totalTracksLength += t.getLengthSeconds();
            }
        }
        return totalTracksLength;
    }

    @Override
    public String toString() {
        return "* MusicSupplier *" +
                "tracks=" + "\n"+tracks +
                '}';
    }
}
