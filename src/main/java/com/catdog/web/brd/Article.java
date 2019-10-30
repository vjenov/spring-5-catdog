package com.catdog.web.brd;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private String articleseq, img, uid, comments, msg, title, content, boardtype, rating;
}
