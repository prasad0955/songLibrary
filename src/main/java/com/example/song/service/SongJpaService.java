/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */

// Write your code here
package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.song.model.Song;
import com.example.song.repository.SongJpaRepository;
import com.example.song.repository.SongRepository;

@Service
public class SongJpaService implements SongRepository {
	@Autowired
	private SongJpaRepository songJpaRepository;

	@Override
	public ArrayList<Song> getSongs() {
		List<Song> songList = songJpaRepository.findAll();
		ArrayList<Song> songs = new ArrayList<>(songList);
		return songs;
	}

	@Override
	public Song getSongById(int songId) {
		try {
			Song song = songJpaRepository.findById(songId).get();
			return song;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Song addSong(Song song) {
		songJpaRepository.save(song);
		return song;
	}

	@Override
	public Song updateSong(int songId, Song song) {
		try {
			Song savedSong = songJpaRepository.findById(songId).get();
			if (song.getSongName() != null) {
				savedSong.setSongName(song.getSongName());
			}
			if (song.getLyricist() != null) {
				savedSong.setLyricist(song.getLyricist());
			}
			if (song.getSinger() != null) {
				savedSong.setSinger(song.getSinger());
			}
			if (song.getMusicDirector() != null) {
				savedSong.setLyricist(song.getLyricist());
			}
			songJpaRepository.save(savedSong);
			return savedSong;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteSong(int songId) {
		try {
			songJpaRepository.deleteById(songId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
}