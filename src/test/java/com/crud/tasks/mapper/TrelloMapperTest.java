package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "TrelloListDto test", false);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("2", "TrelloBoardDto test", lists);
        List<TrelloBoardDto> boards = new ArrayList<>();
        boards.add(trelloBoardDto);
        //When
        List<TrelloBoard> resultList = trelloMapper.mapToBoards(boards);
        int resultSize = resultList.size();
        String resultBoardName = resultList.get(0).getName();
        String resultListName = resultList.get(0).getLists().get(0).getName();
        //Then
        assertNotNull(resultList);
        assertEquals(1, resultSize);
        assertEquals("TrelloBoardDto test", resultBoardName);
        assertEquals("TrelloListDto test", resultListName);
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "TrelloList test", false);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("2", "TrelloBoard test", lists);
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(trelloBoard);
        //When
        List<TrelloBoardDto> resultList = trelloMapper.mapToBoardsDto(boards);
        int resultSize = resultList.size();
        String resultBoardName = resultList.get(0).getName();
        String resultListName = resultList.get(0).getLists().get(0).getName();
        //Then
        assertNotNull(resultList);
        assertEquals(1, resultSize);
        assertEquals("TrelloBoard test", resultBoardName);
        assertEquals("TrelloList test", resultListName);
    }

    @Test
    public void shouldMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "TrelloListDto test", false);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloListDto);
        //When
        List<TrelloList> resultList = trelloMapper.mapToList(lists);
        int resultSize = resultList.size();
        String resultListName = resultList.get(0).getName();
        //Then
        assertNotNull(resultList);
        assertEquals(1, resultSize);
        assertEquals("TrelloListDto test", resultListName);
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "TrelloList test", false);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);
        //When
        List<TrelloListDto> resultList = trelloMapper.mapToListDto(lists);
        int resultSize = resultList.size();
        String resultListName = resultList.get(0).getName();
        //Then
        assertNotNull(resultList);
        assertEquals(1, resultSize);
        assertEquals("TrelloList test", resultListName);
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name test", "description test", "pos test", "list test");
        //When
        TrelloCardDto resultTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        String resultName = resultTrelloCardDto.getName();
        String resultPos = resultTrelloCardDto.getPos();
        String resultListId = resultTrelloCardDto.getListId();
        //Then
        assertEquals("name test", resultName);
        assertEquals("pos test", resultPos);
        assertEquals("list test", resultListId);
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("nameDto test", "descriptionDto test", "posDto test", "listDto test");
        //When
        TrelloCard resultTrelloCard = trelloMapper.mapToCard(trelloCardDto);
        String resultName = resultTrelloCard.getName();
        String resultPos = resultTrelloCard.getPos();
        String resultListId = resultTrelloCard.getListId();
        //Then
        assertEquals("nameDto test", resultName);
        assertEquals("posDto test", resultPos);
        assertEquals("listDto test", resultListId);
    }
}