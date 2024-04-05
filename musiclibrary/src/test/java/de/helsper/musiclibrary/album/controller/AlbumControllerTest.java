package de.helsper.musiclibrary.album.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.helsper.musiclibrary.album.entity.model.Album;
import de.helsper.musiclibrary.album.service.AlbumService;
import de.helsper.musiclibrary.common.Given;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = AlbumController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void AlbumController_CreateAlbum_ReturnCreated() throws Exception {
        Album album = Given.getAlbumWithValues();

        given(albumService.create(ArgumentMatchers.any())).willReturn(album);

        ResultActions response = mockMvc.perform(post("/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(album.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist", CoreMatchers.is(album.getArtist())));
    }

    @Test
    public void AlbumController_FindAllAlbums_ReturnAlbums() throws Exception {
        when(albumService.findAll()).thenReturn(Given.getSeveralAlbums());

        mockMvc.perform(get("/albums").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(2)));
    }

    @Test
    public void AlbumController_FindAlbumById_ReturnAlbum() throws Exception {
        Album album = Given.getAlbumWithValues();

        when(albumService.findById(1L)).thenReturn(album);

        ResultActions response = mockMvc.perform(get("/albums/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(album.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist", CoreMatchers.is(album.getArtist())));
    }

    @Test
    public void AlbumController_UpdateAlbumById_ReturnAlbum() throws Exception {
        Album album = Given.getAlbumWithValues();

        when(albumService.update(1L, album)).thenReturn(album);

        ResultActions response = mockMvc.perform(put("/albums/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(album)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void AlbumController_DeleteAlbumById_ReturnAlbum() throws Exception {
        doNothing().when(albumService).deleteById(1L);

        ResultActions response = mockMvc.perform(delete("/albums/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
