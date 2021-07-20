package net.skhu.devdogs.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchDto {

    private String searchType;
    private String searchContent;

    public SearchDto(String searchType, String searchContent) {
        this.searchType = searchType;
        this.searchContent = searchContent;
    }

}
