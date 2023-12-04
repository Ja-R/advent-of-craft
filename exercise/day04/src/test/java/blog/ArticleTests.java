package blog;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {

    private static final String ARTICLE_NAME = "Lorem Ipsum";
    private static final String ARTICLE_CONTENT = "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore";
    private static final String COMMENT_TEXT = "Amazing article !!!";
    private static final String COMMENT_AUTHOR = "Pablo Escobar";
    private final Article article = new Article(ARTICLE_NAME, ARTICLE_CONTENT);

    @Test
    void add_comment_should_add_given_comment_with_the_date_of_the_day() {
        article.addComment(COMMENT_TEXT, COMMENT_AUTHOR);

        assertThat(article.getComments())
                .hasSize(1)
                .anyMatch(comment -> comment.text().equals(COMMENT_TEXT)
                        && comment.author().equals(COMMENT_AUTHOR)
                        && comment.creationDate().equals(LocalDate.now()));
    }

    @Test
    void add_comment_should_throw_an_exception_when_adding_existing_comment() {
        article.addComment(COMMENT_TEXT, COMMENT_AUTHOR);

        assertThatThrownBy(() -> article.addComment(COMMENT_TEXT, COMMENT_AUTHOR))
                .isInstanceOf(CommentAlreadyExistException.class);
    }
}
