<h1 align="center">Leaf Contributions API</h1>

<p align="center">
  <a href="https://github.com/Im-Tae"><img alt="Author" src="https://img.shields.io/badge/author-Im--Tae-red.svg"/></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
   <a href="https://travis-ci.com/github/Im-Tae/leaf-contributions"><img alt="build" src="https://travis-ci.com/Im-Tae/leaf-contributions.svg?branch=master"/></a>
</p>
<p align="center">  
A simple API that returns number of GitHub contributions</p>



## Use

**request**

```
https://leaf-contributions.herokuapp.com/user/{github name}
```

**receive**

```json
{
  "years": [
    {
      "year": "2020",
      "total": 558,
      "range": {
        "start": "2020-01-01",
        "end": "2020-12-31"
      }
    },
    ...
  ],
  "contributions": [
    {
      "date": "2020-01-01",
      "count": 2,
      "color": "#9be9a8"
    },
    {
      "date": "2020-01-02",
      "count": 1,
      "color": "#9be9a8"
    },
    {
      "date": "2020-01-03",
      "count": 1,
      "color": "#9be9a8"
    },
    ...
  ]
}
```



## License

```
Copyright 2020 Im-Tae (TaeGeon Lim)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```