# Introduction #

From android 4.0 onwards android.permission.read\_logs is not available, which made applocker not able to detect applications launching.

# Details #

DetectorService.java is the main class that was edited to detect the foreground running activity. The permission used is android.permission.get\_tasks.

References used:
1. http://stackoverflow.com/questions/2166961/determining-the-current-foreground-application-from-a-background-task-or-service
2. http://stackoverflow.com/questions/10630737/how-to-stop-a-thread-created-by-implementing-runnable-interface