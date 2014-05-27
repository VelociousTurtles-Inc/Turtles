#!/bin/bash

# remove Created by comments
find -iname '*.java' | xargs sed ':a;N;$!ba;s/[^\n]*\n[^\n]*Created by[^\n]*\n[^\n]*\n*//g' -i

# remove multiple newlines (more than 2 in a row)
find -iname '*.java' | xargs sed ':a;N;$!ba;s/\n\n\n*/\n\n/g' -i
