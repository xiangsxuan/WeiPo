﻿using System.Collections.Generic;
using Newtonsoft.Json;

namespace WeiPo.Services.Models
{
    public partial class HotflowChildModel
    {
        [JsonProperty("ok")]
        public long Ok { get; set; }

        [JsonProperty("data")]
        public List<CommentModel> Data { get; set; }

        [JsonProperty("total_number")]
        public long TotalNumber { get; set; }

        [JsonProperty("max_id")]
        public long MaxId { get; set; }

        [JsonProperty("max_id_type")]
        public long MaxIdType { get; set; }

        [JsonProperty("max")]
        public long Max { get; set; }

        [JsonProperty("rootComment")]
        public List<CommentModel> RootComment { get; set; }
    }
}