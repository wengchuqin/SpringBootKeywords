package top.chuqin.keywords.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EntranceUrlList {
    private static Logger LOGGER = LoggerFactory.getLogger(EntranceUrlList.class);

    public List<String> getData() {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    EntranceUrlList.class.getResourceAsStream("/entranceUrl.data")));
            br.lines().forEach(s -> list.add(s.trim()));
        } catch (Exception e) {
            LOGGER.error("读取entranceUrl.data失败", e);
        }
        LOGGER.debug("EntranceUrlList：{}", list);
        return list;
    }
}
