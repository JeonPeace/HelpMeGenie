package com.jeonpeace.helpmegenie.book.service;

import org.springframework.stereotype.Service;

@Service
public class BookService {

	private static final String BASE_URL = "http://www.aladin.co.kr/ttb/api/";
	private static final String TTB_KEY = "ttbjeonpeace05222134001";
	private static String QUERY = "";
	private static String ISBN = "";
	
	private static final String ITEM_LIST_URL =
			BASE_URL + "ItemList.aspx?ttbkey=" + TTB_KEY + "&Query=" + QUERY + "&QueryType=Title&MaxResults=10&start=1&SearchTarget=Book&output=js&Version=20131101&Cover=big";
	private static final String ITEM_LOOK_UP_URL =
			BASE_URL + "ItemLookUp.aspx?ttbkey=" + TTB_KEY + "&itemIdType=ISBN&ItemId=" + ISBN + "&output=js&Version=20131101&Cover=big";
	
	
	
}
