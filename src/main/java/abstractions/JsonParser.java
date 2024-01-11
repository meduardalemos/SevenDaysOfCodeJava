package abstractions;

import java.util.List;

public interface JsonParser {
    void parseJsonToListOfContents(List<Content> contentList);
}
