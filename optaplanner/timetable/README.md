# Oktaplanner
> Demo implementation according to oktaplanner docu


## Example Request

```
curl -i -X POST http://localhost:8080/timeTable/solve -H "Content-Type:application/json" -d '{"timeslotList":[{"dayOfWeek":"MONDAY","startTime":"08:30:00","endTime":"09:30:00"},{"dayOfWeek":"MONDAY","startTime":"09:30:00","endTime":"10:30:00"}],"roomList":[{"name":"Room A"},{"name":"Room B"}],"lessonList":[{"id":1,"subject":"Math","teacher":"A. Turing","studentGroup":"9th grade"},{"id":2,"subject":"Chemistry","teacher":"M. Curie","studentGroup":"9th grade"},{"id":3,"subject":"French","teacher":"M. Curie","studentGroup":"10th grade"},{"id":4,"subject":"History","teacher":"I. Jones","studentGroup":"10th grade"}]}'
```

### Guides
The following guides illustrate how to use some features concretely:

* [Oktaplanner Docu](https://docs.optaplanner.org/8.0.0.Final/optaplanner-docs/html_single/index.html#springBootJavaQuickStart)
* [Oktaplanner Website](https://www.optaplanner.org/)
