@echo on
SET var=%cd%
SET version="0.0.1"
IF exist %var%\build\libs\modid-1.0.jar (
  ren "%var%\build\libs\modid-1.0.jar" "UsefulClipboard-1.18.2-%version%.jar"
  cd %var%\build\libs\
  start .
 ) ELSE (
 echo "No se encontro el archivo especificado."
)