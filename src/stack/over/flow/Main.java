package src.stack.over.flow;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Tag {
  int id;

  String content;

}

class User {
  int id;

  String username;
}


class Answer {

  int id;

  String content;

  List<Answer> sub_answers = new ArrayList<>();

  int created_by;

  long created_time;

  int updated_by;

  long updated_time;

  AtomicInteger up_vote = new AtomicInteger(0);

  AtomicInteger down_vote = new AtomicInteger(0);

  /**
   * Should use static here because it should belong to the class, not the instance
   */
  private static final AtomicInteger idCounter = new AtomicInteger(0);

  public synchronized boolean addSubAnswer(String content, int createdBy) {
    Answer answer = new Answer();
    answer.id = idCounter.incrementAndGet();
    answer.content = content;
    answer.created_time = System.currentTimeMillis();
    answer.created_by = createdBy;
    answer.updated_by = created_by;
    answer.updated_time = System.currentTimeMillis();
    return sub_answers.add(answer);
  }

  public void upVote(int upVoteBy) {
    updated_time = System.currentTimeMillis();
    updated_by = upVoteBy;
    up_vote.incrementAndGet();
  }

  public synchronized void downVote(int upVoteBy) {
    updated_time = System.currentTimeMillis();
    updated_by = upVoteBy;
    down_vote.incrementAndGet();
  }
}

class Question {

  int id;

  String title;

  String description;

  int created_by;

  AtomicInteger up_vote = new AtomicInteger(0);

  AtomicInteger down_vote = new AtomicInteger(0);

  List<Tag> tags = new ArrayList<>();

  List<Answer> answers = new ArrayList<>();

  int updated_by;

  long updated_time;

  long created_time;

  private final AtomicInteger idCounter = new AtomicInteger(0);

  public synchronized boolean addAnswer(String content, int createdBy) {
    Answer answer = new Answer();
    answer.id = idCounter.incrementAndGet();
    answer.content = content;
    answer.created_time = System.currentTimeMillis();
    answer.created_by = createdBy;
    return answers.add(answer);
  }

  public void upVote(int upVoteBy) {
    updated_time = System.currentTimeMillis();
    updated_by = upVoteBy;
    up_vote.incrementAndGet();
  }

  public void downVote(int upVoteBy) {
    updated_time = System.currentTimeMillis();
    updated_by = upVoteBy;
    down_vote.incrementAndGet();
  }

}

class QuestionManager {
  private static QuestionManager instance;


  private ConcurrentHashMap<Integer, Question> storage;

  private AtomicInteger idCounter;

  private QuestionManager() {
    storage = new ConcurrentHashMap<>();
    idCounter = new AtomicInteger(0);
  }

  public static synchronized QuestionManager getInstance() {
    if (instance == null) {
      instance = new QuestionManager();
    }
    return instance;
  }

  public synchronized boolean addQuestion(String title, String description, int createdBy) {
    int id = idCounter.incrementAndGet();
    Question question = new Question();
    question.id = id;
    question.title = title;
    question.description = description;
    question.created_by = createdBy;
    question.created_time = System.currentTimeMillis();
    storage.put(id, question);
    return true;
  }

  public List<Question> search(String description, List<Tag> tags) {
    return storage.values().stream()
      .filter(data -> data.description.contains(description))
      .filter(data -> tags.isEmpty() || data.tags.stream().anyMatch(tag -> tags.stream().anyMatch(it -> it.id == tag.id)))
      .collect(Collectors.toList());
  }

  public List<Question> search(String title, List<Tag> tags, int userId, int page, int limit) {
    int offSet = page * limit;
    Predicate<Question> searchByTitle = question -> title.isEmpty() || question.title.toLowerCase().contains(title.toLowerCase().trim());
    Predicate<Question> searchByTags = question -> tags.isEmpty() || question.tags.stream().anyMatch(tag -> tags.stream().anyMatch(it -> it.id == tag.id));
    Predicate<Question> searchByUserId = question -> userId == 0 || question.created_by == userId;
    Predicate<Question> combinedFilter = searchByTitle.and(searchByTags).and(searchByUserId);
    return storage.values().stream()
      .filter(combinedFilter)
      .skip(offSet)
      .limit(limit)
      .sorted(Comparator.comparingLong(a -> a.updated_time))
      .collect(Collectors.toList());
  }
}


public class Main {
}
