#!/bin/bash
#
# Copyright (c) 2014 RONDHUIT Co.,Ltd.
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.
#

if [ ! -e api ]; then
  echo "You need to build javadoc via ant commons-javadoc before executing me."
  exit 1
fi

WORK=javadoc-release

rm -Rf $WORK
git clone --branch gh-pages https://github.com/kojisekig/rondhuit-commons.git $WORK; cd $WORK

rm -Rf api
cp -R ../api .
git add api
git commit -m "publish new javadoc"
git push

cd ..;rm -Rf $WORK
