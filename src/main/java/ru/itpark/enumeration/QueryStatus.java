package ru.itpark.enumeration;

public enum QueryStatus {

        ENQUEUED("Ожидает выполнения"),
        INPROGRESS("Выполняется"),
        DONE("Выполнено");

        private String title;

        QueryStatus(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

}
