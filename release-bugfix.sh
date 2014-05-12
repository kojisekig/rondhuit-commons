#!/bin/bash
#
# Copyright (c) 2014 RONDHUIT Co.,Ltd.
#

if [ -z $1 ] || [ -z $2 ] || [ -z $3 ]; then
  echo "Usage: ./release-bugfix.sh <this_branch> <this_release> <next_release>"
  echo "  ex) ./release-bugfix.sh 1.1 1.1.0 1.1.1"
  exit 1
fi

# e.g. 1.1.0, 1.1.1
THIS_BRANCH_NUM=$1
THIS_REL_NUM=$2
NEXT_REL_NUM=$3
#echo "THIS_REL_NUM : $THIS_REL_NUM"
#echo "NEXT_REL_NUM : $NEXT_REL_NUM"

echo -n "Have you executed ant test and has it successfully done? (y/n) "
read ANS
if [ $ANS != "y" ]; then
  exit 1
fi

DATE_Y=$(date +"%Y")
DATE_M=$(date +"%m")
DATE_D=$(date +"%d")
THIS_REL_DATE="${DATE_Y}-${DATE_M}-${DATE_D}"

# make sure on the target release branch
git checkout "rel-${THIS_BRANCH_NUM}"

# set the proper release date
sed -e s/YYYY-MM-DD/$THIS_REL_DATE/ CHANGES.txt > CHANGES.txt.temp
mv CHANGES.txt.temp CHANGES.txt

# commit the modification
git add .
git commit -m "prepare rel-${THIS_REL_NUM}"
git push --set-upstream origin "rel-${THIS_BRANCH_NUM}"

# tag the commit point to rel-${THIS_REL_NUM}
git tag -a "rel-${THIS_REL_NUM}" -m "release tag for ${THIS_REL_NUM}"
git push origin "rel-${THIS_REL_NUM}"

# prepare the next bug fix release on the release branch
git checkout "rel-${THIS_BRANCH_NUM}"
sed -e "1d" CHANGES.txt > CHANGES.txt.temp
cat<<EOF> CHANGES.txt
RONDHUIT COMMONS Java Library change history

========== ${NEXT_REL_NUM} / YYYY-MM-DD ===================================

Important Notice

New Features & Improvements

Bug Fixes

API Changes

Javadoc Fixes

Project environments

Tests

Deprecated/Deleted Features

Others

EOF
cat CHANGES.txt.temp >> CHANGES.txt
rm CHANGES.txt.temp
sed -e s/$THIS_REL_NUM/$NEXT_REL_NUM/ version.properties > version.properties.temp
mv version.properties.temp version.properties
git add .
git commit -m "prepare the next release ${NEXT_REL_NUM}"
git push

echo -e "\\n\\n\\nThe rel-${THIS_REL_NUM} has been almost prepared. Please execute the following to finalize.\\n"
echo "1. build RONDHUIT-COMMONS-${THIS_REL_NUM}.jar file by executing ant"
echo "2. Go to https://github.com/kojisekig/rondhuit-commons/releases/tag/rel-${THIS_REL_NUM}"
echo "3. click [Edit tag] button and drop down the jar file to the drop down box and click [Publish release]"
echo -e "\\nThe download link will be  https://github.com/kojisekig/rondhuit-commons/releases/download/rel-${THIS_REL_NUM}/RONDHUIT-COMMONS-${THIS_REL_NUM}.jar"