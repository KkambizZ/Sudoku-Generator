# Sudoku-Generator
This Program generates an arbitrary Sudoku puzzle in each run

##To have an arbitrary-size puzzle
Simply pass the SIZE to the ```SudokuGenerator(SIZE)``` in the ```main``` class of the code

##How to make an arbitrary build in Eclipse
- Right click on ```pom.xml``` file, go to ```Run As``` and click on ```Maven build...```
- Then in the ```Goals``` tab type ```clean verify```

##How to run a working build
Notice: All builds is a ```4*4``` Sudoku puzzle

- Open terminal
- Navigate to ```Working_builds``` directory
- Navigate to the directory of choice
- run this command:
```sh
$ java -cp build_name.jar Sudoku.MyMain   
```
//replace build_name with the exact name of the build
