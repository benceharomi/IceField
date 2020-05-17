@echo off
title IceField Tester

echo Welcome to the nullpointer team's IceField game tester!
javac src/character/*.java src/enums/*.java src/field/*.java src/item/*.java src/main/*.java src/map/*.java src/team/*.java -d bin
:main
echo.
echo Enter the number of the test case which would you like to start:
echo  1) assembletest
echo  2) bearattack
echo  3) bearmove
echo  4) blizzardtest1
echo  5) blizzardtest2
echo  6) blizzardtest3
echo  7) blizzardtest4
echo  8) buildtent
echo  9) deploynotent
echo 10) dignoshovel
echo 11) digshovel
echo 12) eatteszt1
echo 13) eatteszt2
echo 14) eatteszt3
echo 15) endturntest1
echo 16) eskimospecial
echo 17) explorerspec
echo 18) flip
echo 19) getitemtest
echo 20) holeAndSave
echo 21) holeAndScubaSuit
echo 22) holedie
echo 23) move
echo 24) nosnow
echo 25) shovelbreaks
echo 26) Exit
echo.
set /p input=Enter a number:

if  %input%==1  goto assembletest
if  %input%==2  goto bearattack
if  %input%==3  goto bearmove
if  %input%==4  goto blizzardtest1
if  %input%==5  goto blizzardtest2
if  %input%==6  goto blizzardtest3
if  %input%==7  goto blizzardtest4
if  %input%==8  goto buildtent
if  %input%==9  goto deploynotent
if  %input%==10 goto dignoshovel
if  %input%==11 goto digshovel
if  %input%==12 goto eatteszt1
if  %input%==13 goto eatteszt2
if  %input%==14 goto eatteszt3
if  %input%==15 goto endturntest1
if  %input%==16 goto eskimospecial
if  %input%==17 goto explorerspec
if  %input%==18 goto flip
if  %input%==19 goto getitemtest
if  %input%==20 goto holeAndSave
if  %input%==21 goto holeAndScubaSuit
if  %input%==22 goto holedie
if  %input%==23 goto move
if  %input%==24 goto nosnow
if  %input%==25 goto shovelbreaks
if  %input%==26 goto exit
cls
echo "%input%" is not an option, try again.
goto main

:assembletest:
cls
java -cp bin main.Main < test/input/assembleTest_input.txt >nul
cls
echo *Result of assembletest*
echo.
fc /n test\expected\assembleTest_expected.txt test\output\assembleTest.txt
echo.
echo.
goto main

:bearattack:
cls
java -cp bin main.Main < test/input/bearattack_input.txt >nul
cls
echo *Result of bearattack*
echo.
fc /n test\expected\bearattack_expected.txt test\output\bearattack.txt
echo.
echo.
goto main

:bearmove:
cls
java -cp bin main.Main < test/input/bearmove_input.txt >nul
cls
echo *Result of bearmove*
echo.
fc /n test\expected\bearmove_expected.txt test\output\bearmove.txt
echo.
echo.
goto main

:blizzardtest1:
cls
java -cp bin main.Main < test/input/blizzardtest1_input.txt >nul
cls
echo *Result of blizzardtest1*
echo.
fc /n test\expected\blizzardtest1_expected.txt test\output\blizzardtest1.txt
echo.
echo.
goto main

:blizzardtest2:
cls
java -cp bin main.Main < test/input/blizzardtest2_input.txt >nul
cls
echo *Result of blizzardtest2*
echo.
fc /n test\expected\blizzardtest2_expected.txt test\output\blizzardtest2.txt
echo.
echo.
goto main

:blizzardtest3:
cls
java -cp bin main.Main < test/input/blizzardtest3_input.txt >nul
cls
echo *Result of blizzardtest3*
echo.
fc /n test\expected\blizzardtest3_expected.txt test\output\blizzardtest3.txt
echo.
echo.
goto main

:blizzardtest4:
cls
java -cp bin main.Main < test/input/blizzardtest4_input.txt >nul
cls
echo *Result of blizzardtest4*
echo.
fc /n test\expected\blizzardtest4_expected.txt test\output\blizzardtest4.txt
echo.
echo.
goto main

:buildtent:
cls
java -cp bin main.Main < test/input/buildtent_input.txt >nul
cls
echo *Result of buildtent*
echo.
fc /n test\expected\buildtent_expected.txt test\output\buildtent.txt
echo.
echo.
goto main

:deploynotent:
cls
java -cp bin main.Main < test/input/deploynotent_input.txt >nul
cls
echo *Result of deploynotent*
echo.
fc /n test\expected\deploynotent_expected.txt test\output\deploynotent.txt
echo.
echo.
goto main

:dignoshovel:
cls
java -cp bin main.Main < test/input/dignoshovel_input.txt >nul
cls
echo *Result of dignoshovel*
echo.
fc /n test\expected\dignoshovel_expected.txt test\output\dignoshovel.txt
echo.
echo.
goto main

:digshovel:
cls 
java -cp bin main.Main < test/input/digshovel_input.txt >nul
cls
echo *Result of digshovel*
echo.
fc /n test\expected\digshovel_expected.txt test\output\digshovel.txt
echo.
echo.
goto main

:eatteszt1:
cls
java -cp bin main.Main < test/input/eatteszt1_input.txt >nul
cls
echo *Result of eatteszt1*
echo.
fc /n test\expected\eatteszt1_expected.txt test\output\eatteszt1.txt
echo.
echo.
goto main

:eatteszt2:
cls
java -cp bin main.Main < test/input/eatteszt2_input.txt >nul
cls
echo *Result of eatteszt2*
echo.
fc /n test\expected\eatteszt2_expected.txt test\output\eatteszt2.txt
echo.
echo.
goto main

:eatteszt3:
cls
java -cp bin main.Main < test/input/eatteszt3_input.txt >nul
cls
echo *Result of eatteszt3*
echo.
fc /n test\expected\eatteszt3_expected.txt test\output\eatteszt3.txt
echo.
echo.
goto main

:endturntest1:
cls
java -cp bin main.Main < test/input/endturntest1_input.txt >nul
cls
echo *Result of endturntest1*
echo.
fc /n test\expected\endturntest1_expected.txt test\output\endturntest1.txt
echo.
echo.
goto main

:eskimospecial:
cls
java -cp bin main.Main < test/input/eskimospecial_input.txt >nul
cls
echo *Result of eskimospecial*
echo.
fc /n test\expected\eskimospecial_expected.txt test\output\eskimospecial.txt
echo.
echo.
goto main

:explorerspec:
cls
java -cp bin main.Main < test/input/explorerspec_input.txt >nul
cls
echo *Result of explorerspec*
echo.
fc /n test\expected\explorerspec_expected.txt test\output\explorerspec.txt
echo.
echo.
goto main

:flip:
cls
java -cp bin main.Main < test/input/flip_input.txt >nul
cls
echo *Result of flip*
echo.
fc /n test\expected\flip_expected.txt test\output\flip.txt
echo.
echo.
goto main

:getitemtest:
cls
java -cp bin main.Main < test/input/getitemtest_input.txt >nul
cls
echo *Result of getitemtest*
echo.
fc /n test\expected\getitemtest_expected.txt test\output\getitemtest.txt
echo.
echo.
goto main

:holeAndSave:
cls
java -cp bin main.Main < test/input/holeAndSave_input.txt >nul
cls
echo *Result of holeAndSave*
echo.
fc /n test\expected\holeAndSave_expected.txt test\output\holeAndSave.txt
echo.
echo.
goto main

:holeAndScubaSuit:
cls
java -cp bin main.Main < test/input/holeAndScubaSuit_input.txt >nul
cls
echo *Result of holeAndScubaSuit*
echo.
fc /n test\expected\holeAndScubaSuit_expected.txt test\output\holeAndScubaSuit.txt
echo.
echo.
goto main

:holedie:
cls
java -cp bin main.Main < test/input/holedie_input.txt >nul
cls
echo *Result of holedie*
echo.
fc /n test\expected\holedie_expected.txt test\output\holedie.txt
echo.
echo.
goto main

:move:
cls
java -cp bin main.Main < test/input/move_input.txt >nul
cls
echo *Result of move*
echo.
fc /n test\expected\move_expected.txt test\output\move.txt
echo.
echo.
goto main

:nosnow:
cls
java -cp bin main.Main < test/input/nosnow_input.txt >nul
cls
echo *Result of nosnow*
echo.
fc /n test\expected\nosnow_expected.txt test\output\nosnow.txt
echo.
echo.
goto main

:shovelbreaks:
cls
java -cp bin main.Main < test/input/shovelbreaks_input.txt >nul
cls
echo *Result of shovelbreaks*
echo.
fc /n test\expected\shovelbreaks_expected.txt test\output\shovelbreaks.txt
echo.
echo.
goto main

:exit:
echo Bye