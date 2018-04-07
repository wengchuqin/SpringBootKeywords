package top.chuqin.keywords.service;

import top.chuqin.keywords.domain.ExtractKeywordResult;
import top.chuqin.keywords.domain.Summary;

public interface IExtractKeyword {
    ExtractKeywordResult extract(Summary summary);
}
