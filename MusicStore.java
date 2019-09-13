package assignment4;

import java.util.ArrayList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
    private MyHashTable<String, Song> titleTable = new MyHashTable<String, Song>(100);
    private MyHashTable<String, ArrayList<Song>> artistTable = new MyHashTable<String, ArrayList<Song>>(100);
    private MyHashTable<Integer, ArrayList<Song>> yearTable = new MyHashTable<Integer, ArrayList<Song>>(100);
    //ADD YOUR CODE ABOVE HERE
    
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
        for(Song song: songs) {
        	    this.titleTable.put(song.getTitle(), song);///add title and songs to the hashtable
        	    String artist = song.getArtist();
        	    if(artistTable.get(artist) == null) {//this artist is not in the table 
        	    	   ArrayList<Song> songArr = new ArrayList<Song>();//create a list for this artist
        	    	   songArr.add(song);//and add this song to the list
        	    	   this.artistTable.put(artist, songArr);//add this list to the artists table
        	    	   
        	    }
        	    else {//this artist already exists
        	    	    ArrayList<Song> tmp = this.artistTable.get(artist);//find the song list of this artist
        	    	    tmp.add(song);//add this song to the list 
        	    	    this. artistTable.put(artist, tmp);//put this list back to the artists table
        	    }
        	    Integer year = song.getYear();
        	    if(yearTable.get(year) == null) {//we do the exactly same thing for the year
        	    	   ArrayList<Song> songArr2 = new ArrayList<Song>();
        	    	   songArr2.add(song);
        	    	   yearTable.put(year, songArr2);
        	    }
        	    else {
        	    	     ArrayList<Song> tmp2 = yearTable.get(year);
        	    	     tmp2.add(song);
        	    	     yearTable.put(year, tmp2);
        	    }
        }
        
      
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
    	//same as the constructor, but add a song instead of a list
        this.titleTable.put(s.getTitle(), s);
        String sArtist = s.getArtist();
        if(this.artistTable.get(sArtist) == null) {
	    	   ArrayList<Song> sArr = new ArrayList<Song>();
	    	   this.artistTable.put(sArtist, sArr);
	    } 
        if(this.artistTable.get(sArtist) != null) {
        	   this.artistTable.get(sArtist).add(s);
        }
        int sYear = s.getYear();
        if(this.yearTable.get(sYear) == null) {	   
           ArrayList<Song> sArr2 = new ArrayList<Song>();
	    	   this.yearTable.put(sYear, sArr2);
        }
        if(this.yearTable.get(sYear) != null) {
        	   this.yearTable.get(sYear).add(s);
        }
        
        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
    	    
    	    
        return this.titleTable.get(title); 
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
    	    
        return this.artistTable.get(artist);
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
     	
        return this.yearTable.get(year);
     
        //ADD CODE ABOVE HERE
        
    }
}
