#!/bin/bash
#
# Copyright (c) 2014 RONDHUIT Co.,Ltd.
#

if [ -z $1 ] || [ -z $2 ]; then
  echo "Usage: ./release-master.sh <this_release> <next_release>"
  echo "  ex) ./release-master.sh 1.1 1.2"
  exit 1
fi

# e.g. 1.1, 1.2
THIS_BRANCH_NUM=$1
NEXT_BRANCH_NUM=$2

# e.g. 1.1.0, 1.2.0, 1.1.1
THIS_REL_NUM=${THIS_BRANCH_NUM}.0
NEXT_REL_NUM=${NEXT_BRANCH_NUM}.0
NEXT_BUGFIX_NUM=${THIS_BRANCH_NUM}.1
#echo "THIS_REL_NUM : $THIS_REL_NUM"
#echo "NEXT_REL_NUM : $NEXT_REL_NUM"
#echo "NEXT_BUGFIX_NUM : $NEXT_BUGFIX_NUM"

# e.g. 1.1-dev, 1.2-dev
THIS_DEV_NUM=${THIS_BRANCH_NUM}-dev
NEXT_DEV_NUM=${NEXT_BRANCH_NUM}-dev
#echo "THIS_DEV_NUM : $THIS_DEV_NUM"
#echo "NEXT_DEV_NUM : $NEXT_DEV_NUM"

echo -n "Have you executed ant test and has it successfully done? (y/n) "
read ANS
if [ $ANS != "y" ]; then
  exit 1
fi

DATE_Y=$(date +"%Y")
DATE_M=$(date +"%m")
DATE_D=$(date +"%d")
THIS_REL_DATE="${DATE_Y}-${DATE_M}-${DATE_D}"

# make the new release branch from master
git checkout master
git checkout -b "rel-${THIS_BRANCH_NUM}"

# set the proper version number and date
sed -e s/$THIS_DEV_NUM/$THIS_REL_NUM/ version.properties > version.properties.temp
mv version.properties.temp version.properties

sed -e s/$THIS_DEV_NUM/$THIS_REL_NUM/ CHANGES.txt | sed -e s/YYYY-MM-DD/$THIS_REL_DATE/ > CHANGES.txt.temp
mv CHANGES.txt.temp CHANGES.txt

# commit the modification
git add .
git commit -m "prepare rel-${THIS_REL_NUM}"
git push --set-upstream origin "rel-${THIS_BRANCH_NUM}"

# tag the commit point to rel-${THIS_REL_NUM}
git tag -a "rel-${THIS_REL_NUM}" -m "release tag for ${THIS_REL_NUM}"
git push origin "rel-${THIS_REL_NUM}"

# merge the change to master
git checkout master
git merge "rel-${THIS_BRANCH_NUM}"

# prepare the next release on master
sed -e "1d" CHANGES.txt > CHANGES.txt.temp
cat<<EOF> CHANGES.txt
RONDHUIT COMMONS Java Library change history

========== ${NEXT_DEV_NUM} / YYYY-MM-DD ===================================

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
sed -e s/$THIS_REL_NUM/$NEXT_DEV_NUM/ version.properties > version.properties.temp
mv version.properties.temp version.properties
git add .
git commit -m "prepare the next release ${NEXT_BRANCH_NUM}"
git push

# prepare the next bug fix release on the release branch
git checkout "rel-${THIS_BRANCH_NUM}"
sed -e "1d" CHANGES.txt > CHANGES.txt.temp
cat<<EOF> CHANGES.txt
RONDHUIT COMMONS Java Library change history

========== ${NEXT_BUGFIX_NUM} / YYYY-MM-DD ===================================

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
sed -e s/$THIS_REL_NUM/$NEXT_BUGFIX_NUM/ version.properties > version.properties.temp
mv version.properties.temp version.properties
git add .
git commit -m "prepare the next release ${NEXT_BUGFIX_NUM}"
git push

echo -e "\\n\\n\\nThe rel-${THIS_REL_NUM} has been almost prepared. Please execute the following to finalize.\\n"
echo "1. build RONDHUIT-COMMONS-${THIS_REL_NUM}.jar file by executing ant"
echo "2. Go to https://github.com/kojisekig/rondhuit-commons/releases/tag/rel-${THIS_REL_NUM}"
echo "3. click [Edit tag] button and drop down the jar file to the drop down box and click [Publish release]"
echo -e "\\nThe download link will be  https://github.com/kojisekig/rondhuit-commons/releases/download/rel-${THIS_REL_NUM}/RONDHUIT-COMMONS-${THIS_REL_NUM}.jar"
