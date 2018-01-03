# Student-Array
Student Array Service
----
This program allows for the sorting and searching of an array of Students (A class which contains a students; Name and Examination Score)

The purpose of the program is for lecturers to input, either via a text file or through the standard input, data about students examination results and then sort the data.

The program then sorts the data alphabetically according to name and display's the resulting list in the console. Then you are able to

- Display average score
- Retrieve score for specified student
- Create and display separate merit list

How it works
----
On running StudentArrayService.java you are prompted with
```
How would you like to enter data | MANUAL or FILE :
```
To manually enter type Manual, you can then enter how many students you are going to enter and then the students. Then there name and exam score as you are prompted

To insert from a file type File, then type in the name of the text file, without the extension.

The file itself should be placed in the root folder, and contain the number of students on the first line then on each subsequent line a individual student record should be placed (A students name can also only be one word). A sample data file is provided in data.txt

Once the data is in the program you are able prompted with

```
What would you like to do? | AVERAGE | SEARCH |  MERIT | PRINT | EXIT:
```
Here you can continuously go through the different options, once finished type exit.

Average produces the average score of the student Array

Search allows you to search the list for a particular student (Done via a binary search)

Merit will print a list of students sorted by there scores

Print will print out the students currently in the array sorted by students names alphabetically
