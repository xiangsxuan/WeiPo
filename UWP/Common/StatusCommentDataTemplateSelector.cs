﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using WeiPo.Services.Models;

namespace WeiPo.Common
{
    public class StatusCommentDataTemplateSelector : DataTemplateSelector
    {
        public DataTemplate StatusTemplate { get; set; }
        public DataTemplate CommentTemplate { get; set; }

        protected override DataTemplate SelectTemplateCore(object item)
        {
            switch (item)
            {
                case StatusModel status:
                    return StatusTemplate;
                case CommentModel comment:
                    return CommentTemplate;
            }
            return new DataTemplate();
        }
    }
}
